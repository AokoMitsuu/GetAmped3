	� G   �       �W�o�D?�R%���{��tb;�~(�a��
t�hW�F{�Xq<�=I?P)�j��\�\�*��9QK�/x�c;I�mw�&r�o޼��~���=N};B-b∓ŷ�����xmn��,��8]ܢ��oN���<5�Exg|�&�L1"�O�|���p��p�������u�Ӑp�����<JTG7���ڧ���ݺR&�����d,�i�F��>���RX��%��k5Yģ��am1ߧ�;ċh��;!���a�<��a��5̐�>sm9(����(�v#�	�b^8E���b�g�a/ޟ!�ۥj5^��+��E���I����K9m1�#AD��H:���3RnM5�mK5�tMng�24�?�Yt�W�ԝ���5Z�c%%���h�k��rU0y^Ǖ8Q��&j��(�=[��FՉI�G��XϷUc���Qg�uY��g#"�m.�[��
JB訕�4����5�n�X!�:���f�˧�O�GOO~����N�P�x��%�P>��(K���ڶ$�ܯ�z��%�lQ����Uli�(`�vR�%t#��V[��`V�P�u��l����]YA]��F�S�i`{a���y�jS�S�Z;�E��f�cm������R�o�P�,� �(������f\��,�#Q���خn4vE8��'W0��Ee�$D3YF$�n(G3�	L�;0a�2?��r���=fu>��H�Ƶ�?��rfH�߾ۤ���Q����$m����B�^�r��_�/>;�������ξ�������q���p ��I����٫�"��}VVQ@�E���՗v?d�+pB�P���x�pҨ�q�߸Gxo��j����>�+�>>�44�g�����I��Bߎ����Rx�]A������2v��Ç+dK�JH��;��_`2A�d�_N����m�%��(*�tnF��z�jn�R��I%���R���p���C�d\G�P�:j��~�=��n�q=OZv� �K
�Me|>\f=�'�ƘGFz��3�����XF�7�b�8
I�ș� ����xļ�H�Ģ�Av����iT!�q���|��??���~�����*���N���-'���o(� �ӒT%���k����G�mPڙ��xf@��Lu3�Y��&�0f�
�&T-�3U��"兩O��k%)�+�B@�dd��pR�!�߈� )�I�i�pE������	V<h���rw���|��[mb��-��5��V�_����V� \�J3���4}�T���4���f�p��2+�fa�'���Q   �  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; leafD !/accs/bamboo/bamboo_leaf_main.scm�    @       ?333?333?333              leaf2D !/accs/bamboo/bamboo_leaf_main.scm�    ?�      ?333?333?333              start damagehitdir broken 
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
 d � 3D /efx/smoke/smoke_sand27.ptc�    ��33    >���>���>���C{&�          crack11D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��             D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��C4            attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �в � 	hit_goza2  d�  �     � 2-    ?�      �в �  d�  �     � 2 setJudgmentKind
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
-� �W* � �W*,� rW*� �W*� �W� 8tH<��
?L��    >�33?�  >�33�  