	� G   �       �WOo�6?'@�}N�H�CKr�?0|h� ��v]R�j�e�EE���C΀�E;l-���]�K���۶�c$˾�Eɒl'M�v�bC�������{�{w����61q�I�⛽��[V���0��r�e��.nS��ק����l�����M8��b<-?����'�Ꮳ�o��wשOC�]�7@y�8������W�O����u�L,+*�H��X����q}Y=��H�KfI�j(��GU/��b�O-^w��
wBJ�׃�Y̫�0p�
0�g�\��C/��E�o���E����"��u1������Q���k�01����ͭ��Ӆ���� ��f$�օ�9�	w�����+�&��E���":֫Zj�)j�Gr[I�=c*ZjZ&q�X /�����x�2vxE��� {�Qu��#�[��۪��Z`��+G=֥�ٌ�o��8�VW�A�����p�T7f�F��kJ
�\>9z::|z��Og;wBQ�ew�4C��tܲL��_�mE�ܯ�z.������ �&�uL"̜���Nr���}��U`E�#
�nԐMz �n�p��#+�+��l�p� ��A
=NA�w���uQ���Q���wy�$q|Է�=��rwi*uE�'� 	U��+�o&���r<u�m���FsW��XT�5�| ��j���d^8(��t;h��P�2�	��m�A`^�g�,����x�cV��E�ތ���C�����i��������d&aˑM"~z���7������?��~>�:����ǟ��G�뗣!�������W��>�Tl��,�4� �M�Ͱ=����B�P��3r���(�Q�㰾q���4j2�p� �� ?�4�������I���ш6?~�6XS;����L\���[�薠JH��;��xA��R`q5՟wD gm�S��"(@�z���esS�4M",H��~�2�1�zH�%��1J0��&��ב��<-��]��$����sZvI�[��L��E�3lb6&,2�Ǆ�9!g���:����1)$�#ٺ	������<oKlĲ�AV���iT!�q�A=���~x9>;�����3h��q�O�i��������ML�βn��*��3/,J�d�8"o���t���0�2Y����ch�c�.���Q+���N�R\'�ҵ���%�R��̈��E�ICV�gA���M�����O�Mo|'��A��P��[w��7�3]����n�ar�n��9�s�-��P��%�3�����P��KE�_���6K����x���/LJK   �  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; leafD !/accs/bamboo/bamboo_leaf_main.scm�    @       ?333?333?333              leaf2D !/accs/bamboo/bamboo_leaf_main.scm�    ?�      ?333?333?333              start damagehitdir broken 
setVisible (Z)V
 f o p
 H 0 3 anime (F)V
 d s t@�   clearGenerate
 d w 1D  /accs/bamboo/bamboo_collapse.scm�            ?�  ?�  ?�               rndc (F)F
 H z { g hpb (FFF)V
 } ~ C4   scale
 } � t=L�� generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � start2D /accs/bamboo/bamboo_leaf.scm�    ����    ?�  ?�  ?�                
start_leaf (FF)V
 d s �A   >�� 	playSound
 d � 3D /efx/smoke/smoke_sand27.ptc�    ��33    >���>���>���C#�5          crack11D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��             D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��C4            attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            ��� � hit_bc  d�  �     � 2-    ?�      ��� �  d�  �     � 2 setJudgmentKind
 f � 3 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow01_white7.ptc�    ?�      ?�  @@  ?�                enableCollision
 f � p clearJudgmentKind
 f � 1 lockItemScript
 f � p lockTimeCount
 f � p vibe (FFFFI)V
 d � �=��� getArgv ()Ljava/lang/Object;
 f � � getPosition ()Lkotori/geom/Position;
 f � � Lkotori/geom/KMath; � getDir (FF)F
 � � � R2D
 � � { 	 � Q 	 � S  Lkotori/geom/Position; � dir	 � � �� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate2
 d � B�   ()Lkotori/geom/Vector3D;
 H  � D2R
 � � { sin
 H � { cos
 H � { (FFF)Lkotori/geom/Vector3D;
 � + �>��� getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1D  /accs/bamboo/bamboo_collapse.scm�            ?�  ?�  ?�               end2 startScript (Ljava/lang/String;)Z
 � � � 	crack_end<#�
C   yquake
 d � 3D /accs/bamboo/bamboo_leaf.scm�@   �       ?�  ?�  ?�                	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � �-        ?�  ��� � hit_be  d�  �     c d-        ?�ff��� �  d�  �     c dD /efx/flash/glow01_white6.ptc�@   �       ?�  @@  ?�          B�   
 f � 1 
stopScript
 f � 1 setBlend
 d � 3 setShadowState
 d � 3 setEdge
 f � p 	fadeColor
 d � � start3D /efx/flash/glow01_white6.ptc�    ?       ?�  @@  ?�                f h g j g - 1  	i� k��  l 1  �*� qW*d� rW*� qW*v� uW*� xWyL+�� |� �W+�� |b� �W**+�� �� �W*��� �W*� rW*��� �W*� �W*��� �W**��� �� �W**��� �� �W*��� �W*��� �W*d� rW*� �W*� l� �W*� l� �W*�l� �W*�l� �W*�� �W�  m 1	 e*� �W*� �W*� �W*� �W*�\ ȶ �W*� �W*� �L*� �M+� �+� �� �� �,� ø �fF�:� �:%� �W*%�f� �W� �:�%� Ҹ �j�%� Ҹ �j� �W*� �:� 	� �W*�� �W*d� rW*d� rW*d� rW*d� rW*� �:*� �:� � �W� � �W*F� rW*���� ȶ �W*� �W*� �W*�l� �W*� �W*�� �W*�� �W*� �W*d� rW*,� rW*� �W*,� rW*� �W*� �W�  n 1  �*� �W*� �W*� �W*� �W*� rW*� �W*� �W*� �W* ȶ �W*� �L+� 
+�� �W*� �M*� �N,� 
,� �W-� 
-� �W* � �W*,� rW*� �W*� �W� 8tEm<��
?L��    >�33?�  >�33�  