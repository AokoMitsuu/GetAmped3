	� G   �       �WMo�6>'@��N�H�CKr�>�u�mi�.)r5h��ˢ"�N�!g�֢��v�.ݥۀ`�m��1�e�b/Eɒl'm���bC�ȗ/���ߋ����61q�I�⛽��[V���0��r�e��.nS��ק����l�����M8��b<-?���ǣ�O�����w֩OC�]�7@y�8������S������u�L,+*�H��Xs�z�M߄|�G\GVO�,R��Yҵ�,�QU��0���S���E��BƝ�����v��X\��0�F�����s��n�@Q,��d@�H5t]��<���s�w{T��c�Z�2L,�j<;mz�)�v�-�y$��4I�ua�0gN*ĝ����m�Ɗ���lQz���������Z5����VRb���Z�I\(r�:��a+�� 9-ޠ��_�s�6�,lT�x*������j��u��Q�u�|6#���2�U��S�IG�,���؍��b������(�O��������>��P���%�P>'�,���b[8��:����_���ބ�.�I��s��I��е��[����}HA׍�I`�vydu�7["�0 �u��A�S�]�C�n]Tn�@fT�(�m5�F>��������4��"��Z��*��K�W�zUM9�:�6Իn4w�;�E-'�а��Ce*�
�Y�4�n([4�	t����3?�Ӳ��O{��~q����m1� ��m��i��������d&a�5�D���דo�>y���w�l4|y��/Ǐ���ׯFCh�2��O_>���{P�泰�@ �6�7��@��"W���\Kg��1�QH���a}�.��i6�d��+�~�ih1/Z185���>7��y;��6?y�����ly�O-�|x�V6�%����Nz�0��W
,�����myJ��PT�\�\?��an�R���C���6C �,3�.���$/I��{��:j��~�}��i�u=O"��C�%.��2�&^g<�4��ƄaFz��3'������|��*q��*�M�	������<oKlĲ�A���iT!�q����ۿ?���|����)�*l���n��:���o*� ��ے�%���닒�GdqPڙf��5f��1N�lơ�,]���Q+o���N�R\5����d��d)G'3"����R��Us�Y�d�4�B�*��|bx��-jE��m޺�v�!���V��lw�g��kv;��e�!���BhL�n�D���a��㗊�nJ���,n:3zśL�� ���u   �  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; leafD (/accs/bamboo_yellow/bamboo_leaf_main.scm�    @       ?333?333?333              leaf2D (/accs/bamboo_yellow/bamboo_leaf_main.scm�    ?�      ?333?333?333              start damagehitdir broken 
setVisible (Z)V
 f o p
 H 0 3 anime (F)V
 d s t@�   clearGenerate
 d w 1D '/accs/bamboo_yellow/bamboo_collapse.scm�            ?�=�?�=�?�=��ӚU         rndc (F)F
 H z { g hpb (FFF)V
 } ~ C4   scale
 } � t=L�� generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � start2D #/accs/bamboo_yellow/bamboo_leaf.scm�    ����    ?�  ?�  ?�                
start_leaf (FF)V
 d s �A   >�� 	playSound
 d � 3D /efx/smoke/smoke_sand27.ptc�    ��33    >���>���>���C%�w          crack11D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��             D /efx/stage/iwa_r/iwa_r.scm�    �@      ?L��?   ?L��C4            attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
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
 � � 1D '/accs/bamboo_yellow/bamboo_collapse.scm�            ?�  ?�  ?�               end2 startScript (Ljava/lang/String;)Z
 � � � 	crack_end<#�
C   yquake
 d � 3D #/accs/bamboo_yellow/bamboo_leaf.scm�@   �       ?�  ?�  ?�                	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
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
-� �W* � �W*,� rW*� �W*� �W� 8tWN<��
?L��    >�33?�  >�33�  