	� G 8/accs/072syuriken_b_green/mdl/syuriken_b_effect_yoko.kmd��       �TKo�@�!%R���\�H��&*�>�6n��'����MIZ吖[�āH\x		8� ~�[$�c;N��Y����7���r�|�K]RW� ]PV��=��P�b�H�0`B�"`T�� �U㲹��֟و�'+�����qk}s-�'X����&�HJ��3�գR� `�(��U�P;��
��2f(x�xgk�n��n��Y��Zk�c<M��)�-t�/~�ރ����D
�P��ޑ�����ߟ=�x�T	�V�a�O=����ו+��V�V�4��Ukiu�o���#U���ޞ�zq��������O�ɻ��Mt�8:����N΢Ƀh�<�܏&/���h�:�<�D��d� W�[�X��{�bY\ծ��h�h|�cv����u�,��-�l�kep��9
�A��4�0���$4����q�m=�6-ו�%�� 9�H٘��$"��f_�� �!�5$���1
����Z�A$M��i� rCHQV\7�-�z�������@�@M��>/~R�
A��,%M:#Uo�d#�8=���,�x��QƷi�eC
)0<6�rH"�+l��x8���x���!6d#�!��rꙋ��u-��Z�"�rV]�~r=����ۣd	bn�ln��h�Gǎ�MT���lΒ��1�+K�:��i݅���y�[����,~�<�=.]�"5���m�)K��G��av_�d�_�a�)���Y����0�1�Y   E  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� Lkotori/gfx/KGLScriptModel; beambeam start hit hitstage 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f o p
 H 0 3 anime (F)V
 d s t roll (Lkotori/geom/Vector3D;I)V
 f v w-    ��       under12	 d z  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d | }D 3/accs/072syuriken_b_green/syuriken_all_beambeam.scm�            ?�  ?�  ?�               effect_yoko kill
 f � 1 	playSound
 d � 3 setColor (FFFF)V
 d � � rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               finishAllKGL
 f � 1D 3/accs/072syuriken_b_green/syuriken_all_beambeam.scm�            ?�  ?�  ?�               vibe (FFFFI)V
 d � ��#�
CH   attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            � '� � hit_c1  d�   �     c d 	fadeColor
 d � �-            � � �  d�   �     c d setBlend
 d � 3 setRenderType
 d � 3-            � d� � hit_be  d�   �     c d /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d | �D /efx/explode/exp_sphere1.scm�            ?   ?   ?                
setVisible (Z)V
 f � � f h g k j - 1  i� �  l 1  :*�� qW*� rW*� uW*y� xW*� {� *�� ~�*ж rW*� �W�  m 1  *� rW*� �W�  n 1  �*�� qW�   *� �W*�� �W*� �W*� rW*� {� *��� ~�*� �W*�� ȶ �W*��� �W* ȶ rW*X� �W* ȶ rW*��� �W*�� rW*� �W*� �W*� �W*d� rW*��� �W*�� �W*� �W*d� rW*� �W*�� rW*� �W� ��?�            >��o=c�0>��o� � changemodel� =/accs/072syuriken_b_green/mdl/syuriken_b_effect_yoko_beam.kmd